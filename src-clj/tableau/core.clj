(ns tableau.core)

(defn draw [n cards]
  [(take n cards)
   (nthrest cards n)])

(defn put-card-in-hand [card player hands]
  {player (assoc (hands player) 0 card)})

(defn deal [n cards hands]
  (let [players (keys hands)]
    (loop [new-hands hands rest cards cnt n]
      (if (zero? cnt)
        [new-hands rest]
      (let [[delt remainder] (draw (count players) rest)]
        (recur (apply merge (map #(put-card-in-hand %1 %2 new-hands) delt players)) remainder (dec cnt)))))))

;;(defn deal [n cards hands]
;;  (loop [rest cards
;;         cnt (* n (count hands))
;;         new-hands hands]
;;    (if (zero? cnt)
;;        [rest new-hands]
;;      (recur (second (draw 1 rest))
;;             (dec cnt)
;;             new-hands))))
