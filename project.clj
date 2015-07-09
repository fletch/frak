(defproject frak "0.1.7-SNAPSHOT"
  :description "Transform collections of strings into regular expressions."
  :url "http://github.com/noprompt/frak"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :jar-exclusions [#"(?:\.(?:cljx|sw[onp])|cli\.cljs?)"]
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [org.clojure/tools.cli "0.3.1"]]
  :plugins [[lein-cljsbuild "1.0.3"]
            [com.keminglabs/cljx "0.3.0" :exclusions [org.clojure/clojure]]]
  :source-paths ["src/cljx"]
  :profiles {:dev {:dependencies [[criterium "0.4.3"]
                                  [com.keminglabs/cljx "0.6.0"]]
                   :repl-options {:nrepl-middleware [cljx.repl-middleware/wrap-cljx]}
                   :hooks [cljx.hooks]}}
  :cljx {:builds [{:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :clj}
                  {:source-paths ["src/cljx"]
                   :output-path "target/classes"
                   :rules :cljs}]}
  :cljsbuild {:builds [{:id "browser"
                        :source-paths ["target/classes"]
                        :compiler {:output-to "target/js/frak.min.js"
                                   :optimizations :advanced 
                                   :pretty-print false}}
                       {:id "node"
                        :source-paths ["target/classes"]
                        :compiler {:output-to "bin/frak"
                                   :externs ["resources/externs/process.js"]
                                   :optimizations :advanced
                                   :pretty-print false
                                   :target :nodejs}}]} 
  :main frak.cli
  :repl-options {:init-ns frak})
