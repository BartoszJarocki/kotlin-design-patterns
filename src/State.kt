class Context(var state: State = UnauthorizedState()) {
    var isAuthorized: Boolean = false
        get() {
            return state.isAuthorized(context = this)
        }


    var userId: String? = null
        get() {
            return state.userId(context = this)
        }


    fun changeStateToAuthorized(userId: String) {
        state = AuthorizedState(userId = userId)
    }

    fun changeStateToUnauthorized() {
        state = UnauthorizedState()
    }
}

interface State {
    fun isAuthorized(context: Context): Boolean
    fun userId(context: Context): String?
}

class UnauthorizedState : State {
    override fun isAuthorized(context: Context) = false

    override fun userId(context: Context) = null
}

class AuthorizedState(var userId: String) : State {
    override fun isAuthorized(context: Context) = true

    override fun userId(context: Context) = userId
}

fun main(args: Array<String>) {
    val userContext = Context()

    userContext.changeStateToAuthorized(userId = "admin")
    println("${userContext.isAuthorized}, ${userContext.userId}")

    userContext.changeStateToUnauthorized()
    println("${userContext.isAuthorized}, ${userContext.userId}")
}