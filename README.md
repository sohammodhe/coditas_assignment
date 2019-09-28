# coditas_assignment

Created application using springboot
Created a Middleware service(interface) which can also have other api integrated into itself
Have only integrated GITHUB API
But code is as such which can also integrate with gitlab API
Response strucure is map with its key either GITHUB/GITLAB
Its value will be the list of map of all projects
Have implemented Error handling, for now just created dummy error codes 
Have also used logger
Have used junit & mockito for unit testing

To test :-
Run it as Springboot application 
and hit the URL :- http://localhost:8070:/repos/users/sohammodhe (it will fetch all the github public repos i have in my account) 
to fetch all public github repos :- http://localhost:8070/repos/
