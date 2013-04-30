(defproject tableau "0.0.1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :source-paths ["src-clj"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                [compojure "1.0.4"]
                [hiccup "1.0.3"]]
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-ring "0.7.0"]]
  ;;:hooks [leiningen.cljsbuild]
  :cljsbuild {
    :builds [],
    :build {
      :dev
        {:source-paths ["src-cljs"]
         :jar true
         :compiler {:output-to "resources/public/js/main.js"
                    :optimizations :whitespace
                    :pretty-print true}}
    :repl-listen-port 9000
    :repl-launch-commands {
      "safari" ["safari"
                :stdout ".repl-safari-out"
                :stderr ".repl-safari-err"]}}}
  :ring {:handler tableau.routes/app})
