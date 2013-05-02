(ns tableau.core-test
  (:require [clojure.test :refer :all]
            [tableau.core :refer :all]))

(def ^:dynamic cards [])

(def ^:dynamic players [])

(def ^:dynamic hands [])

(defn setup-cards [f]
  (binding [cards [:ace :king :queen :jack]]
  (f)))

(defn setup-players [f]
  (binding [players [:player1 :player2]
            hands {:player1 [] :player2 []}]
  (f)))

(use-fixtures
  :each setup-cards setup-players)

(deftest test-dealing
  (println (put-card-in-hand (first cards) :player1 hands))
  (testing "Test dealing 1 card to each player"
    (let [[new-hands rest] (deal 1 cards players hands)]
      (println "dealing")
      (println new-hands)
      ;;(is (= (new-hands :player1) [:ace]))
      ;;(is (= (new-hands :player2) [:king]))
      ))
  (println cards)
  (println players)
  (println hands)
  )

(deftest test-drawing
  (testing "Test drawing 1 card"
    (let [[card rest] (draw 1 cards)]
      (is (= card [:ace]))
      (is (= rest [:king :queen :jack]))))
  (testing "Test drawing 2 cards"
    (let [[card rest] (draw 2 cards)]
      (is (= card [:ace :king]))
      (is (= rest [:queen :jack])))))

(deftest test-shuffling
  (testing "Test shuffling")
  )
