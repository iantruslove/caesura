(ns caesura.core-test
  (:require [clojure.test :refer :all]
            [caesura.core :as cb]))


(defn err? [v]
  (boolean (first v)))

(defn ok? [v]
  (not (err? v)))

(defn breaker-open? [v]
  (-> v
      :breaker
      :open-since
      boolean))

(defn breaker-closed? [v]
  (not (breaker-open? v)))

(deftest test-circuit-breakage
  (testing "when max-failures is exceeded, the circuit breaker trips"
    (let [call-count (atom 0)
          always-throw (fn []
                         (swap! call-count inc)
                         (throw (Exception.)))
          breaker (cb/new-circuit-breaker {:call-timeout 5000 #_msecs
                                           :reset-timeout 60000 #_msecs
                                           :max-failures 3})]
      (let [three-fails (-> {:error nil :value nil :breaker cb}
                            (cb/run-fn always-throw)
                            (cb/run-fn always-throw)
                            (cb/run-fn always-throw))
            _ (is (breaker-closed? three-fails))
            four-fails (cb/run-fn three-fails always-throw)
            _ (is (breaker-open? four-fails))
            ]
        )
      
      )))
