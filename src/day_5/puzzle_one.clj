(ns day-5.puzzle-one
  (:require [clojure.string :as str]))

(defn parse-cargo-stacks [s]
  (let [x (->> (str/split-lines s)
               (butlast)
               (map #(partition-all 4 %))
               (map #(interleave (range 1 10) %))
               (mapcat #(into {} (partition-all 2) %))
               (group-by key))]
    (into {} (for [[k v] x]
               {k (->> (map val v)
                       (flatten)
                       (reverse)
                       (filter #(re-find #"[A-Z]" (str %)))
                       (into []))}))))

(defn parse-rearrangement-procedure [s]
  (mapv parse-long (re-seq #"\d+" s)))

(defn move-cargo-stacks [stacks [move-count from to]]
  (let [from-stack (stacks from)
        to-stack (stacks to)
        [new-from cargo-on-crane] (split-at (- (count from-stack) move-count) from-stack)
        new-to (concat to-stack (reverse cargo-on-crane))]
    ;; First we update the 'from' stack
    ;; then we add the cargo to the 'to' stack.
    (-> (assoc stacks from new-from)
        (assoc to new-to))))

(defn supply-stacks-top-cargo [stacks-s command-s]
  (let [stacks (parse-cargo-stacks stacks-s)
        commands (map parse-rearrangement-procedure (str/split-lines command-s))]
    (->> (reduce (fn [acc x] (move-cargo-stacks acc x)) stacks commands)
         (sort-by key)
         (vals)
         (map last)
         (str/join))))
