Данное приложение сравнивает текущий курс валюты со вчерашним по запросу. Пример запроса: *http://localhost:8100/api/rub*. В ответ на такой запрос возвращается гифка, соответствующая результату сравнения. 

Для запуска необходимо добавить в файл *application.properties* персональные ключи API, которые выдаются при регистрации:
*exchangeApiKey* - https://openexchangerates.org <br/>
*giphyApiKey* - https://developers.giphy.com

Обращаю внимание, что в бесплатной версии API нет возможности изменять базовую валюту (по отношению к которой определяется курс). По умолчанию базовой валютой является usd, однако при необходимости ее можно сменить в файле *application.properties*.

Также к загразке доступен Docker-образ. Для этого необходимо установить сам Docker, и выполнить в коммандной строке "docker pull eugenesmsk/exchangerate:0.0.3". Для запуска приложения через Docker требуется также выполнить команду "docker run eugenesmsk/exchangerate:0.0.3"

