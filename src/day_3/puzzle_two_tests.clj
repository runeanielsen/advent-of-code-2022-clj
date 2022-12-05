(ns day-3.puzzle-two-tests
  (:require [day-3.puzzle-two :as sut]
            [clojure.java.io :as io]
            [clojure.test :refer [deftest is are testing]]))

;; Handle number representations for items in the rucksack.
(deftest rucksack-item-values-are-correct
  (are [x y] (= x y)
    16 (sut/item-priorities \p)
    38 (sut/item-priorities \L)
    42 (sut/item-priorities \P)
    22 (sut/item-priorities \v)
    20 (sut/item-priorities \t)
    19 (sut/item-priorities \s)))

;; Find shared items in rucksack compartments
(deftest find-shared-item-in-rucksack-compartments
  (are [x y] (= x y)
    \p (sut/shared-item ["vJrwpWtwJgWr" "hcsFMMfFFhFp"])
    \L (sut/shared-item ["jqHRNqRjqzjGDLGL" "rsFMfFZSrLrFZsSL"])
    \P (sut/shared-item ["PmmdzqPrV" "vPwwTWBwg"])
    \v (sut/shared-item ["wMqvLMZHhHMvwLH" "jbvcjnnSBnvTQFn"])
    \t (sut/shared-item ["ttgJtRGJ" "QctTZtZT"])
    \s (sut/shared-item ["CrZsJsPPZsGz" "wwsLwLmpwMDw"])))

;; Split the list of rucksacks
(deftest can-seperate-elfs-into-groups
  (let [elfs "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"]
    (is (= [["vJrwpWtwJgWrhcsFMMfFFhFp"
             "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
             "PmmdzqPrVvPwwTWBwg"]
            ["wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
             "ttgJtRGJQctTZtZT"
             "CrZsJsPPZsGzwwsLwLmpwMDw"]]
           (sut/seperate-groups 3 elfs)))))

(deftest reorganization-output-correct-total-priorities
  (testing "Example input."
    (let [rucksacks "vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw"]
      (is (= 70 (sut/reorganize rucksacks)))))

  (testing "User specific input."
    (let [rucksacks (slurp (io/resource "day_3/input.txt"))]
      (is (= 2363 (sut/reorganize rucksacks))))))
