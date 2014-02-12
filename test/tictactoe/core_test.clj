(ns tictactoe.core-test
  (:require [midje.sweet :refer :all]
            [tictactoe.core :refer :all]))

(def emptyboard [nil nil nil
                 nil nil nil
                 nil nil nil])

(def lastround [:x :o :x
                :o :x :o
                :x :o nil])

(facts "x-turn?"
       (fact "Player x turn when empty"
             (x-turn? emptyboard) => truthy)
       (fact "Player o turn when 1 placed"
             (x-turn? (cons :x (take 8 emptyboard))) => falsey)
       (fact "Player x turn when 8 placed"
             (x-turn? lastround) => truthy))

(facts "o-turn?"
       (fact "Player o turn"
             (o-turn? [:x :o :x, nil nil nil, nil nil nil]) => truthy))

(facts "turn"
      (fact "Player x turn when empty"
            (turn emptyboard) => :x)
      (fact "Player o turn when 1 placed"
            (turn (cons :x (take 8 emptyboard))) => :o))

(facts "x-win?"
       (fact "Player x wins when three on first row"
             (x-win? [:x :x :x, nil nil nil, nil nil nil]) => truthy)
       (fact "Player x not win"
             (x-win? [:x :x nil, nil nil nil, nil nil nil]) => falsey))

(facts "o-win?"
       (fact "Win on first row"
             (o-win? [:o :o :o, nil nil nil, nil nil nil]) => truthy))

(facts "winner"
       (fact "x win on first row"
             (winner [:x :x :x, nil nil nil, nil nil nil]) => :x)
       (fact "o win on first row"
             (winner [:o :o :o, nil nil nil, nil nil nil]) => :o)
       (fact "no winner"
             (winner emptyboard) => nil))

(facts "winner rows"
       (fact "first row"
             (winner [:x :x :x, nil nil nil, nil nil nil]) => :x)
       (fact "second row"
             (winner [nil nil nil, :x :x :x, nil nil nil]) => :x)
       (fact "third row"
             (winner [nil nil nil, nil nil nil, :x :x :x,]) => :x))

(facts "winner columns"
       (fact "first column"
             (winner [:o nil nil
                      :o nil nil
                      :o nil nil]) => :o)
       (fact "second column"
             (winner [nil :o nil
                      nil :o nil
                      nil :o nil]) => :o)
       (fact "third column"
             (winner [nil nil :o
                      nil nil :o
                      nil nil :o]) => :o))

(facts "winner diagonals"
       (fact "diagonal 1"
             (winner [:o nil nil
                      nil :o nil
                      nil nil :o]) => :o)
       (facts "diagonal 2"
              (winner [nil nil :x
                      nil :x nil
                      :x nil nil]) => :x))

(facts "game-over?"
       (fact "emptyboard => nil"
             (game-over? emptyboard) => falsey)
       (fact "fullboard => true"
             (game-over? [:x :o :x
                          :o :o :x,
                          :x :x :o]) => truthy)
       (fact "a player won => true"
             (game-over? [:x :x :x, nil nil nil, nil nil nil]) => truthy))

