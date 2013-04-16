(defproject tableau "0.0.1"
  :source-paths ["src-clj"]
  :depedencies [[org.clojure/clojure "1.4.0"]
                [compojure "1.0.4"]
                [hiccup "1.0.0"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "1.0.0"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
    :repl-listen-port 9000
    :repl-launch-commands {
      "safari" ["safari"
                :stdout ".repl-safari-out"
                :stderr ".repl-safari-err"]}
    :build {
      :dev
        {:source-paths ["src-cljs"]
         :jar true
         :compiler {:output-to "resources/public/js/main-debug.js"
                    :optimizations :whitespace
                    :pretty-print true}}}}
  :ring {:handler tableau.routes/app})
