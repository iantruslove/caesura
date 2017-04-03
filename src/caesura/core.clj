(ns caesura.core)

;; intended usage

(def cb (new-circuit-breaker {:call-timeout 5000 #_msecs
                              :reset-timeout 60000 #_msecs
                              :max-failures 5}))

(with-breaker cb
  ;; Single sexp
  (println "hello!"))
;; Returns the now-updated circuit breaker. Would be nice if it returned the
;; return value too.

;; But this won't be super-useful, because of the immutability.
