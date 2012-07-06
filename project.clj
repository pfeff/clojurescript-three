(defproject three "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring "1.1.1"]
                 [compojure "1.1.0"]
                 [enlive "1.0.1"]
                 [org.mozilla/rhino "1.7R3"]
                 [com.google.javascript/closure-compiler "r1918"]
                 [org.clojure/google-closure-library "0.0-1376"]]
            :dev-dependencies [[jline "0.9.94"]
                               [marginalia "0.7.1"]
                               [lein-marginalia "0.7.1"]
                               [lein-git-deps "0.0.1-SNAPSHOT"]]
            :git-dependencies [["https://github.com/clojure/clojurescript.git"]]
            :plugins [[lein-cljsbuild "0.2.1"]
                      [lein-ring "0.7.1"]]
;            :ring {:handler three.core/app}
            :ring {:handler three.dev-server/app}
            :repl-init three.repl
            :source-path "src/app/clj"
            :extra-classpath-dirs ["src/app/cljs"
                                   "src/app/cljs-macros"
                                   "src/lib/clj"
                                   "src/lib/cljs"
                                   "templates"
                                   ".lein-git-deps/clojurescript/src/clj"
                                   ".lein-git-deps/clojurescript/src/cljs"])

