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

(= [1 2 3] [1 2 3])

; True
(= ["hello" 2 3] [(str "hell" "o") 2 3])

; False
(= [2 3 4] [1 2 3])

; False
(not= ["hello" 2 3] [(str "hell" "o") 2 3])

; COLLECTIONS:
; ** remember you must run the top name-sake of the file
; before you can run any lines in the REPL (cmd + shift + p)

;LISTS:
(list "Dog" 1 3.4 true) ; "Dog" 1 3.4 2

; First index of a list
(first (list "cat" 3 4 5.2))                                ; "cat"

; All indices after the fist of a list
(rest (list "cat" 3 4 5.2))                                ; (3 4 5.2)

; Index of a list
(nth (list "cat" 3 4 5.2) 2)                                ; 4
(nth (list "cat" 3 4 5.2) 0)                                ; "cat"

;Add to a list
(list* 1 2 3 4 [5 6 7])                                     ; (1 2 3 4 5 6 7)

; Add a single value to left side of list
(cons 4 (list 1 2 3))

; SETS:

(set '(1 1 1 1 2))                                          ; {1 2} - sets return unique values only

; For me, this is returning th value if exists, else nil
(get (set '(1 2 2 5 1 6)) 0)                        ; nil
(get (set '(2 3)) 2)                                ; 2

; this joins a value to the set
(conj (set '(3 4 5)) 1)                                     ; {1 4 3 5}

; Check if set contains specific value
(contains? (set '(1 3 4 2)) 3)                              ; true
(contains? (set '(1 3 4 2)) 6)                              ; false

; Remove (dis-join) a value from a set
(disj (set '(1 3 2 4)) 3)                                   ; {1 4 2}


; VECTORS:

; simple vector (array)
(vector 1 "dog")                                            ; [1 "dog"]

; Get specific index from a vector
; **Note vectors are 0-indexed
(get (vector 1 2 3 4) 2)                                    ; 3

; Append an element to a vector
(conj (vector 1 2 3) 4)                                     ; [1 2 3 4]

; To remove an element from a vector
; will pop off the top of the stack
(pop (vector 2 4 6 8))                                      ; [2 4 6]

; To return value from one index point to another
(subvec (vector 1 2 3 4 5) 1 3)                             ; [2 3]
(subvec (vector 1 2 3 4 5) 1 2)                             ; [2]

; MAPS:
; collections of key-value pairs

(hash-map "Name" "John" "Age" 42 "key" "value")             ; {"Age" 42, "key "value", "Name" "John"}

; Sorted by key
(sorted-map 1 "John" 3 42 2 "value")             ; {1 "John", 2 "value", 3 42}

; Get value based on mapped key
(get (hash-map "Name" "John" "Age" 42) "Name")              ; "John"

; Get a key-value pair for key
(find (hash-map "Name" "John" "Age" 42) "Age")              ; ["Age" 42]

; Check if map contains a key
(contains? (hash-map "Name" "John" "Age" 38 "Food" "Popsicles") "Age") ; true

; Return list of keys
(keys (hash-map "Name" "John" "Age" 38 "Food" "Popsicles"))   ; ("Age" "Food" "Name")

; Return list of values
(vals (hash-map "Name" "John" "Age" 38 "Food" "Popsicles"))   ; (38 "Popsicles" "John")

; Merge hash-maps
(merge-with + (hash-map "Name" "John") (hash-map "Age" 38))   ; {"Age" 38, "Name" "John"}

; Atoms:



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
