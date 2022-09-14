package com.example.movieappmvvm.ui.profile

//@HiltViewModel
//@ExperimentalCoroutinesApi
//class ProfileViewModel @Inject constructor(var firebaseAuth: FirebaseAuth , var database: DatabaseReference) {
//
//    private val _registerStatus = MutableLiveData<Boolean>()
//    val registerStatus: LiveData<Boolean> get() = _registerStatus
//
//
//
//
//
//     fun initializeFirebase() {
//        var firebaseUser = firebaseAuth.currentUser!!
//
//        database = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)
//    }
//    @OptIn(ExperimentalCoroutinesApi::class)
//    fun getUserName(): Flow<Resource<String>> = callbackFlow {
//        val listener = database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val userName = snapshot.child("name").value.toString()
//            }
//            override fun onCancelled(error: DatabaseError) {
//                trySend(Resource.error("Exception"))
//
//            }
//        })
//
//        awaitClose {
//            database.removeEventListener(listener)
//        }
//    }
//
//}