
### UTILISATION ###

Base de données : Mysql
username : servermobile
password : servermobile

( Vous pouvez changez ces valeurs dans le fichier properties dans src\main\resources )
( Vous pouvez générer automatiquement les tables en ajoutant la ligne suivante dans le fichier properties lors de la première exécution du serveur : spring.jpa.hibernate.ddl-auto= create-drop )


#------------------------------ SERVICE UTILISATEUR ------------------------------#


Pour ajouter un utilisateur : (POST)
	URL : http://localhost:8080/api/membre/add

	Le body doit comporter :
		un pseudo
		un email
		un password

	exemple :
		{
			"username": "elymne",
        	"email": "elymne@gmail.com",
        	"password": "password"
		}

Pour récupérer tous les utilisateurs : (GET)
	URL : http://localhost:8080/api/membre/getAll

Pour récupérer un utilisateur par son identifiant : (GET)
	URL : http://localhost:8080/api/membre/get/{identifiant}

Pour récupérer un utilisateur par son pseudo : (GET)
	URL : http://localhost:8080/api/membre/get/pseudo/{pseudo}

Pour modifier un utilisateur : (PUT)
	URL : http://localhost:8080/api/membre/update/{identifiant}

	le body doit comporter :
		un pseudo
		un email
		un password

		(ceux-ci peuvent rester les mêmes, ça ne changera absolument rien)
		exemple :
		{
			"username": "elymneDeux",
        	"email": "elymne@gmail.com",
        	"password": "passwordNouveau"
		}

Pour supprimer un utilisateur : (DELETE)
	URL : http://localhost:8080/api/membre/delete/{identifiant}


#------------------------------ SERVICE EVENEMENT ------------------------------#


Pour ajouter un evenement : (POST)
	URL : http://localhost:8080/api/evenement/add

	Le body doit comporter :
		un titre
		une date
		un lieu
		un identifiant de d'utilisateur (Le créateur)

	exemple :
		{
			"title": "Experience",
        	"date": "2019-02-26",
        	"place": "somewhere",
        	"user": "a14ed55f58f8854g4g44g4g"
		}

Pour récupérer tous les evenements : (GET)
	URL : http://localhost:8080/api/evenement/getAll

Pour récupérer un evenement par son identifiant : (GET)
	URL : http://localhost:8080/api/evenement/get/{identifiant}

Pour récupérer un evenement par son titre : (GET)
	URL : http://localhost:8080/api/evenement/get/title/{title}

Pour modifier un evenement : (PUT)
	URL : http://localhost:8080/api/evenement/update/{identifiant}

	le body doit comporter :
		un titre
		une date
		un lieu
		un identifiant de d'utilisateur (Le créateur)

		(ceux-ci peuvent rester les mêmes, ça ne changera absolument rien)
	exemple :
		{
			"title": "Experience",
        	"date": "2019-02-26",
        	"place": "somewhere",
        	"user": "a14ed55f58f8854g4g44g4g"
		}

Pour supprimer un evenement : (DELETE)
	URL : http://localhost:8080/api/evenement/delete/{identifiant}

Pour ajouter un utilisateur à l'evenement : (PUT)
	URL : http://localhost:8080/api/evenement/addUser/{identifiant}

	Le body doit comporter :
		un identifiant (Celui de l'utilisateur à ajouter)
		une chaîne de caractère random parce que le formatage Json, c'est parfois con (En gros, écrit ce que tu veux dessus, ça ne changera rien)

		exemple :
			{
				"idObject":"cc471b26-ae07-4796-a690-648471a7c933",
				"typeObject":"user"
			}

Pour retirer un utilisateur à l'evenement : (PUT)
	URL : http://localhost:8080/api/evenement/removeUser/{identitiant}

	Le body doit comporter :
		un identifiant (Celui de l'utilisateur à ajouter)
		une chaîne de caractère random parce que le formatage Json, c'est parfois con (En gros, écrit ce que tu veux dessus, ça ne changera rien)

		exemple :
			{
				"idObject":"cc471b26-ae07-4796-a690-648471a7c933",
				"typeObject":"user"
			}


#------------------------------ SERVICE DEPENSE ------------------------------#


Pour ajouter une dépense : (POST)
	URL : http://localhost:8080/api/depense/add

	Le body doit comporter : 
		une quantité d'argent ( un nombre entier )
		un libelle
		un identifiant d'utilisateur
		un identifiant d'evenement

		exemple :
			{
				"amount":175,
				"wording":"gateaux",
				"userId":"cc471b26-ae07-4796-a690-648471a7c933",
				"eventId":"9399d293-4403-4247-8822-4e4d1aacd434"
			}

Pour récupérer toutes les dépenses : (GET)
	URL : http://localhost:8080/api/depense/getAll

Pour récupérer une dépense par son identifiant : (GET)
	URL : http://localhost:8080/api/depense/get/{identifiant}

Pour récupérer une liste de dépenses par utilisateur : (GET)
	URL : http://localhost:8080/api/depense/get/user/{userId}

Pour récupérer une liste dépenses par evenement : (GET)
	URL : http://localhost:8080/api/depense/get/event/{eventId}

Pour récupérer une dépense par son utilisateur et son evenement : (GET)
	URL : http://localhost:8080/api/depense/get/eventuser/{userId}/{eventId}

Pour modifier une dépense : (PUT)
	URL : http://localhost:8080/api/depense/update/{identifiant}

	Le body doit comporter : 
		une quantité d'argent ( un nombre entier )
		un libelle
		un identifiant d'utilisateur
		un identifiant d'evenement

	exemple :
		{
			"amount":175,
			"wording":"gateaux",
			"userId":"cc471b26-ae07-4796-a690-648471a7c933",
			"eventId":"9399d293-4403-4247-8822-4e4d1aacd434"
		}

Pour supprimer une dépense : (DELETE)
	URL : http://localhost:8080/api/depense/delete/{identifiant}


#------------------------------ SERVICE DETTE (j'ai pas trouvé d'autre nom pour c'truc) ------------------------------#


Pour récupérer les dettes qu'un utilisateur doit à quelqu'un ou que les autres utilisateurs en négatif lui doivent : (GET)
	URL : http://localhost:8080/api/expenseManager/get/{identifiant utilisateur}/{identifiant evenement}

	En gros le résultat si tu dois de l'argent, ça ressemble à ça :
		[
    		{
        		"id": "cc471b26-ae07-4796-a690-648471a7c933",
        		"owing": -32.08510638297872
    		},
    		{
        		"id": "b7f63b8a-74c8-4094-b8da-d997d05e623d",
        		"owing": -12.72340425531915
    		},
    		{
       			"id": "046f188e-ea07-4081-b7e0-89a37e86861a",
        		"owing": -7.191489361702128
    		}
		]

		Ici, notre utilisateur doit 32 balles à cc471b26-ae07-4796-a690-648471a7c933,
		12 balles à b7f63b8a-74c8-4094-b8da-d997d05e623d
		et 7 balles à 046f188e-ea07-4081-b7e0-89a37e86861a

	Et si on te doit de l'argent, ça ressemblerait plutôt à ça :
		[
    		{
        		"id": "a0a30f05-442e-44db-841f-8926aae55c92",
        		"owing": 5.808510638297872
    		},
    		{
        		"id": "7c5060de-14c2-4936-9387-05bbbc641c4b",
        		"owing": 7.191489361702128
    		}
		]

		Ici, a0a30f05-442e-44db-841f-8926aae55c92 doit 5 euro à notre utilisateur
		et 7c5060de-14c2-4936-9387-05bbbc641c4b doit 7 euro à notre utilisateur

		En gros, si c'est négatif, c'est de l'argent que tu dois, si c'est positif, c'est de l'argent qu'on te doit.

		Il est impossible d'avoir du négatif et du positif en résultat.
		Vous pouvez regarder le code dans ExpenseManager pour comprendre pourquoi
		L'explication rapide c'est : 
			Si on te doit de l'argent, c'est que tu es au dessus de la moyenne des dépenses totale, ducoup, tu ne dois de l'argent à personne.