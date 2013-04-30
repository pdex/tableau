(ns tableau.core)

(defn draw [n cards]
  [(take n cards)
   (nthrest cards n)])
