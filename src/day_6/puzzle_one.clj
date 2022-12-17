(ns day-6.puzzle-one)

(defn start-of-packet-marker [s]
  (loop [s (seq s) l 4]
    (if (= 4 (count (set (take 4 s))))
      l
      (recur (rest s) (inc l)))))
