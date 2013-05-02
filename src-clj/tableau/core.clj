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

(defn update-state [current-state event]
  ;;return new state
  )

(defn end-state [start-state events]
  (reduce update-state start-state events))

(defn consequences [current-state event]
  ;; return a sequence of new events
  )

(defn apply-consequences [current-state event]
  (reduce update-state current-state
          (consequences current-state event)))

(defn recursive-consequences [current-state event]
  (reduce (fn [state event]
            (recursive-consequences
              state (update-state state event)))
          current-state
          (consequences current-state event)))

(defn chain-consequences [initial-state consequence-fns]
  (loop [state initial-state
        fs consequence-fns
        output []]
    (if (seq fs)
      (let [events ((first fs) state)
            new-state (reduce update-state state events)]
        (recur new-state (rest fs) (into output events))) output)))
