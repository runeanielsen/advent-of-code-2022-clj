(ns day-6.puzzle)

(defn start-of-marker [n s]
  (loop [s (seq s) l n]
    (if (= n (count (set (take n s))))
      l
      (recur (rest s) (inc l)))))
