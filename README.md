# thetask

## Arhitecture
The instructions were a little prescriptive in regards to the activity / fragment setup and the separation of UI elements that need to communicate to each other which I have followed. 

Additionally I decided to use compose for the UI, Flow for the reactiveness and coroutines for thread manaagement. I have also opted in for use of ViewModel for the ui logic and the fragment / activity communication.

### Repository
There is a repository interface which is implemented by a fake db which just serialises the json to an object. In an actual project this implementation can easily be a ROOM db or something similar. The repositry exposes a mutableStateFlow of reuslts which the VM subscribes to and some utility functions to load and sort. The repository has a deliverate delay to allow to view the handling of the loading / double clicking buttons.

### Viewmodel
The ViewModel subscribes to the repo's flow and maps it into a UI state class which signals the screen of the current state (IDLE, LOADING, SUCCESS) it also exposes to the screen some means to load, sort and click an item

### Screen
The Screen subscribes to the VM UI state and does the composition accordingly. I have composed the screen as such there is no dependency on a vm directly which also makes it easier to preview.

### Tests
I have added a quick unit test for the repo which just checks that the flow emits when it should, in a project I would also consider making the VM not dependent on android so that VM Unit tests can be ran but this requires managing coroutine scopes which I didnt want to get into for this task. 
there are no UI tests as though I feel we can just add previews to the composable to test different ui states. I would however add espresso tests should we have a flow or something particular that we would like to test. 

### DI
I have madu use of koin for 
