# Hytale Post

## Présentation

Etudiant à l'école ESIEA en 3ème année, nous avions à réaliser une application grâce à une API, ici l'API du jeu Hytale https://hytale-api.com/.

Cette application permet ainsi de voir tous les articles du blog d'Hytale (https://hytale.com/news) sur l'écran de démarrage grâce à une RecyclerView.

 ![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/GlobalView.jpg)

Lorsque l'utilisateur clique sur l'un des articles, il accède au détail de l'article. Il y retrouve le titre de l'article, l'image présente sur l'écran précédent et le compte tenu de l'article

 ![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/DetailView.jpg)

Cette application suit les recommandations de Google en utilisant l'architecture MVVM.

Nous devions également stocker le résultat des requêtes HTTP/HTTPS dans le cache. Or l'API d'Hytale ne le permet pas :

![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/PostManCapture.PNG)

## SharedPreferences

J'ai donc regardé comment utiliser les sharedPreferences, j'ai commencé par les initialisés :

![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/FragmentSharedPreferences.PNG)

Puis passé les sharedPreferences au ViewModel grâce à un Factory :

![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/ViewModelFactorySharedPreferences.PNG)

Et enfin ajouté le code correspondant au ViewModel :

![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/ViewModelSharedPreferences.PNG)
![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/ViewModelSharedPreferencesNext.PNG)

J'ai également créer une nouvelel Data class pour manipuler plus simplement juste les données qui m'intéressaient :

![alt text](https://github.com/Ilryss-Lurgorg/HytalePosts/blob/master/pictures/NewDataClassSharedPreferences.PNG)

Comme vous pouvez le voir dans le code du ViewModel, je n'ai pas trouvé d'autres solutions qu'utiliser la methode add qui demande à avoir un MutableLiveData. Ainsi cela impliquait de changer une grande partie de l'application pour peu de résultat. Même en stockant dans le cache le résultat de la requête HTTP/HTTPS, et sans connexion l'utilisateur ne pourrait voir que le titre et le texte s'il est possible de stocker autant de texte dans les sharedPreferences, qui à ce que j'ai compris, ne sont pas faite pour cela au départ mais pour des settings de l'application à sauvegarder.

Ainsi dans le projet que vosu retrouverez sur ce GitHub, je n'ai pas implenté les sharedPreferences qui n'aurait pas eu grande utilité, l'application aurait été sans image et certainement sans texte dans le détail des articles.

## Conclusion

Ce projet contient une liste d'élément avec la possibilités de voir le détail de chaque élément. Pour cela il utilise une API Rest mais ne stocke pas en cache. De plus, elle utilsie l'architecture Singleton et MVVM et a été designé aux même couleurs que le site Hytale (https://hytale.com/)

## Auteur
Steve CHAUVREAU-MANAT 3A 31
