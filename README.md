# football_App


In clean code architecture, there are three main layers
* Data
* Domain
* Presentation along with
common (can also be called as core)

Data
The data layers contains the repository implementation and this layer is closest to actual data sources and is responsible for communication with data sources.

The data layer returns the models and not entities and the model also contains the fromJson and toJson mapper.

Domain
This is innermost layer and will contain the core business logic i.e. usecases and business object also called as entities, the usecase depends on the contract of repository (not implementation)

Presentation
This layer contains all the information about the UI and everything to show to the end user customer. The presentation layer also contains the ViewModel which is the state  and often termed as brain of UI.






# Android APP Security
reverse engineering : used ProGuard

Encryption & Decryption by Keystore 

disable screenshots system (Flag Secure Layout)

hide API keys (Use Build Configurations)

Network Security (validate the server's SSL/TLS certificate)

