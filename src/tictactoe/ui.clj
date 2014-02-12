(ns tictactoe.ui
  (:use [clojure.string]
        [quil.core]
        [tictactoe.core]))

(def emptyboard [nil nil nil
                 nil nil nil
                 nil nil nil])

(def board (atom emptyboard))

(def tick (atom 0))

(def rotate-tick (atom 0))

(defn setup []
  (smooth)
  (frame-rate 30))

(defn draw-box [id]
  (let [row (quot id 3)
        col (rem id 3)
        boxstate (nth @board id)]
    (cond (= boxstate nil) (fill 0 255 255)
          (= boxstate :x) (fill 255 0 0)
          (= boxstate :o) (fill 159 238 0))
    (with-translation [(* col 200) (* row 200) 0]
                      (box 100))))

(defn doturn [key-num]
  (let [player (turn @board)
        id (- key-num 1)]
    (if (nil? (nth @board id))
        (swap! board #(assoc % id player)))))

(defn key-press []
  (try (doturn (Integer/parseInt (str (raw-key))))
       (catch Exception e))
  (if (= "r" (str (raw-key)))
      (reset! board emptyboard)))

(defn check-game [board]
  (fill 255)
  (text-size 28)
  (if (game-over? board)
      (cond (x-win? board) (text "PLAYER X WON!" 300 750)
            (o-win? board) (text "PLAYER O WON!" 300 750)
            :else (text "GAME OVER - NO WINNER!" 210 750))))

(defn draw []
  (swap! tick inc)
  (background 0)
  (lights)
  (check-game @board)
  (fill 255 255 255)
  (stroke 255)
  (push-matrix)
  (text-size 28)
  (text (str "Player " (clojure.string/upper-case (name (turn @board)))) 50 50)
  (translate 400 400 0)
  ;(rotate-x (/ @tick 50))
  ;(rotate-y (/ @tick 75))
  (translate -200 -200 0)
  (doseq [boxid (range 9)]
         (draw-box boxid))
  (pop-matrix))

(defsketch tictactoe
  :title "Tic Tac Toe"
  :setup setup
  :draw draw
  :key-pressed key-press
  :size [800 800]
  :renderer :opengl)

