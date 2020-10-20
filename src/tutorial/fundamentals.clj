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

(def names (str/split-lines (slurp "/usr/share/dict/propernames")))

; Practicing Simple Funtions

; Here we have a function that will join the characters with
; a '-' and reverse the order ** to use with REPL
; we first have to run the line (shift + cmd + p) then we
; can call the function in the REPL window
(def mangle
  (fn [string]
    (str/join "-" (str/reverse string))))

; Another way to write this function is to shorten by including
; defn
(defn mangle2
  [string]
  (str/join "*" (str/reverse string)))

; This will reverse a reversed string
(defn de-mangle
  [mangled-string]
  (str/reverse (str/replace mangled-string "-" "")))

(defn de-mangle2
  [mangled-string]
  (str/reverse (str/replace mangled-string "*" "")))

; In this function we want to determine if a word is a
; palindrome - we use the ? to give it a boolean value
; We use "let" to create some temporary variables only inside this scope
(defn palindrome? [word]
  (let [lower-case (str/lower-case word)
        reversed (str/reverse lower-case)]
    ; This is stating: if the reversed lower-case version of the word is equal
    ; to the plain lower-cased version, return true/else false
    (= reversed lower-case)))





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
