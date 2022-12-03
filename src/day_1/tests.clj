(ns day-1.tests
  (:require [day-1.core :refer [highest-total-calories]]
            [clojure.test :refer [deftest is testing]]
            [clojure.java.io :as io]))

;; First star question
(deftest highest-total-calories-returns-highest-total
  (testing "Getting highest elf output."
    (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
      (is (= (highest-total-calories input 1) 71502))))

  (testing "Getting top three elf output."
    (let [input (slurp (io/resource "day_1/calorie-list.txt"))]
      (is (= (highest-total-calories input 3) 208191)))))
