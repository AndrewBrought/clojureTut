(ns tutorial.fundamentals
  (:require
    [clojure.string :as str]))

; Once the REPL is started we can use shift + cmd + p to
; run a line in the REPL box - BUT - first
; we need to run the doc-name line first

; LISTS


(println "Hello"
         (+ 2 3)
         )

(defn -main
  [& args]

  ; STRiNGS
  (def str1 "tHis is my string 1")

  (println (str/blank? str1))
  (println (str/includes? str1 "my"))
  (println (str/index-of str1 "my"))
  (println (str/split str1 #"\d"))
  (println (str/split str1 #" "))
  (println (str/join " " ["The" "Big" "Lebowski"]))
  (println (str/replace "I am 42" #"42" "665"))
  (println (str/upper-case str1))

  )
