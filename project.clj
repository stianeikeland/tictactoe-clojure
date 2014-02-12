(defproject tictactoe "0.1.0-SNAPSHOT"
  :description "Tic-Tac-Toe kata @ Bergen Coding Dojo"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [quil "1.6.0"]]
  :profiles {:dev {:dependencies [[midje "1.6.0"]]
                   :plugins [[lein-midje "3.1.1"]]}}
  :main tictactoe.ui)
