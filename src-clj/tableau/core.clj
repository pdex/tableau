(ns tableau.core)

(defn draw [n cards]
  [(take n cards)
   (nthrest cards n)])

(defn put-card-in-hand [card player hands]
  {player (assoc (hands player) 0 card)})

(defn put-cards-in-hands [cards players hands]
  (apply merge (map #(put-card-in-hand %1 %2 hands) cards players)))

(defn deal [n cards players hands]
  (loop [new-hands hands rest cards cnt n]
    (if (zero? cnt)
      [new-hands rest]
    (let [[delt remainder] (draw (count players) rest)]
      (recur (put-cards-in-hands delt players new-hands) remainder (dec cnt))))))

;;(defn deal [n cards hands]
;;  (loop [rest cards
;;         cnt (* n (count hands))
;;         new-hands hands]
;;    (if (zero? cnt)
;;        [rest new-hands]
;;      (recur (second (draw 1 rest))
;;             (dec cnt)
;;             new-hands))))
