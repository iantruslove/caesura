(ns caesura.core)

;; intended usage

{:call-timeout 5000 #_msecs
 :reset-timeout 60000 #_msecs
 :max-failures 5}

(defn new-circuit-breaker [{:keys [call-timeout reset-timeout max-failures] :as args}]
  (merge args {:open-since nil}))

(comment
  
  (with-breaker cb
    ;; Single sexp
    (println "hello!")))
;; Returns the now-updated circuit breaker. Would be nice if it returned the
;; return value too.

;; But this won't be super-useful, because of the immutability.

(defn breaker [v]
  (:breaker v))

(defn err [v]
  (:err v))

(defn val [v]
  (:val v))

(defn now []
  (java.util.Date.))

(defn breaker-open-since [breaker]
  (:open-since breaker))

(defn millis-since-breaker-opened [breaker]
  )

(defn run-fn [{:keys [breaker err val]} f]
  )
