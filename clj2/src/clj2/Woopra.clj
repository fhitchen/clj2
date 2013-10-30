(ns clj2.Woopra)



(require '[clj-http.client :as client])
(require '[clojure.data.json :as json])
(require '[clojure.data.xml :as xml])

(def query
(json/write-str {
                 :website "www.aiowireless.com",
                 :date_format "MM/dd/yyyy HH:mm",
                 :start_day   "10/09/2013 10:00",
                 :end_day     "10/09/2013 10:05",
                 :limit 100,
                 :offset 0,
                 :report {
                          :limit 50,
                          :title "Users By Hour",
                          :index 0,
    
                          :columns [
                                    {
                                     :scope "visitors",
                                     :hide false,
                                     :name "People",
                                     :_format "#,##0",
                                     :method "count",
                                     :render "number_format(cell('People'), '#,##0')"
                                     }
                                    ],
                          :group_by [
                                     {
                                      :scope "visits",
                                      :key "time",
                                      :transforms [
                                                   {
                                                    :params [
                                                             "HH"
                                                             ],
                                                    :function "date_format"
                                                    }
                                                   ]
                                      }
                                     ],
                          :order_by "",
                          :render ""
                          }
                 }))

(print query)

(def response
(client/post "http://www.woopra.com/rest/report"
  {:throw-entire-message? false
   :proxy-host "cmiproxy"
   :proxy-port 8080
   :body query
:headers {"X-Api-Version" "2.0"
             "X-Access-Id" "C84TEC26W5CBA4QOAP6OGUPTXR6JNGED"
             "X-Access-Secret" "DbnhWW0Pva8RXzktD8tnDfG266igMRQWWJapOkwLfKj33Io6gE2yVAGrAU1xcaNL"
             }
   :content-type :json 
   :accept :json}))

(json/write-str {:a 1 :b 2})


(print query)

(def body (:body response))

(print body)
(def row (json/read-str body))

(def cells (get row "rows"))
(print cells)

(nth cells 7)

(map #(nth % 7)
     (seq cells))

(print (seq cells))

(foo row)