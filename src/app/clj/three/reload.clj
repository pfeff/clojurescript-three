(ns three.reload
  (:use [cljs.closure :only (build)]
        [three.config])
  (:require [clojure.java.io :as io]))

(defn watch-cljs [handler config]
  (fn [request]
    (let [k (:uri request)
          ts (any-modified-cljs (:src-root config) k)]
      (when ts
        (swap! last-compile assoc k ts)
        (let [build-opts (cljs-build-opts config)]
          (doseq [file (file-seq
                         (io/file (str (:output-dir build-opts) "/"
                                       (:top-level-package config))))]
            (.setLastModified file 0))
          (build (:app-root config) (if (= (:uri request) "/production")
                                      (assoc build-opts :optimizations :advanced
                                             :output-to (production-js config))
                                      build-opts)))))
    (handler request)))


