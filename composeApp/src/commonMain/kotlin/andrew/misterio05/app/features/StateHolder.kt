package andrew.misterio05.app.features

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitDsl
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class StateHolder<S : State>(
    init: S,
    private val reducer: (S, Event) -> ReducerResult<S>,
) {

    private val orbit = object : ContainerHost<S, Effect> {
        override val container = CoroutineScope(Dispatchers.Default).container<S, Effect>(init)
    }

    val state: StateFlow<S> get() = orbit.container.stateFlow
    val events: Flow<Effect> get() = orbit.container.sideEffectFlow

    fun dispatch(event: Event) = intent {
        val result = reducer(state, event)
        reduce { result.state }
        result.effects.forEach { eff -> postSideEffect(eff) }
    }

    @OrbitDsl
    protected fun intent(body: suspend SimpleSyntax<S, Effect>.() -> Unit) =
        orbit.intent(transformer = body)
}
