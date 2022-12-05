(ns day-3.puzzle-two
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(def item-priorities
  "Value priorities for items in the rucksack."
  (letfn [(f [r1 r2] (zipmap (map char r1) r2))]
    (merge (f (range 97 124) (range 1 27))
           (f (range 65 91) (range 27 53)))))

(defn seperate-groups [size s]
  "Seperates the elfs into groups."
  (partition size (str/split-lines s)))

(defn shared-item [xs]
  "Takes the item compartments
and finds the item that is shared between them."
  (first (apply set/intersection (map set xs))))

(defn reorganize [s]
  (let [xf (comp (map shared-item)
                 (map item-priorities))]
    (transduce xf + 0 (seperate-groups 3 s))))
