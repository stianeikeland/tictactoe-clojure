(ns tictactoe.core)

(def win-patterns [[0 1 2] ; 1st row
                   [3 4 5] ; 2nd row
                   [6 7 8] ; 3rd row
                   [0 4 8] ; diagonal
                   [2 4 6] ; diagonal
                   [0 3 6] ; 1st col
                   [1 4 7] ; 2nd col
                   [2 5 8] ; 3rd col
                   ])

(defn x-turn? [board]
  (if (even? (count (filter identity board))) true nil))

(defn o-turn? [board]
  (not (x-turn? board)))

(defn turn [board]
  (if (x-turn? board) :x :o))

(defn extract-pattern [pattern board]
  (map #(nth board %1) pattern))

(defn pattern-player-win? [player pattern board]
  (every? #(= player %1)
          (extract-pattern pattern board)))

(defn player-win? [player board]
  (some true?
        (map #(pattern-player-win? player %1 board)
             win-patterns)))

(defn x-win? [board]
  (player-win? :x board))

(defn o-win? [board]
  (player-win? :o board))

(defn winner [board]
  (cond (x-win? board) :x
        (o-win? board) :o
        :else nil))

(defn game-over? [board]
  (cond (= (count (filter identity board)) 9) true
        (winner board) true
        :else nil))
