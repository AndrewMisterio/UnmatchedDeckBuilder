package andrew.misterio05.app.features

import kotlinx.coroutines.CoroutineScope

interface State
interface Event
interface Screen : State

interface Effect {

    data class NextEvent(val value: Event) : Effect
    companion object {
        fun nextEvent(event: Event): Effect = NextEvent(event)
    }
}

interface ReducerResult<S : State> {
    val state: S
    val effects: Set<Effect> get() = emptySet

    companion object {
        private val emptySet = emptySet<Effect>()

        data class ReducerResultImpl<S : State>(
            override val state: S,
            override val effects: Set<Effect>
        ) :
            ReducerResult<S>

        operator fun <S : State, E : Effect> invoke(state: S, vararg effects: E?) =
            ReducerResultImpl(state, setOfNotNull(*effects))

        operator fun <S : State, E : Effect> invoke(state: S, effects: Set<E?>) =
            ReducerResultImpl(state, effects.filterNotNull().toSet())
    }
}

fun <S : State> S.just() = ReducerResult.invoke(this, emptySet())
infix fun <S : State> S.with(effects: Set<Effect>) = ReducerResult.invoke(this, effects)
infix fun <S : State> S.with(effect: Effect?) = ReducerResult.invoke(this, effect)
infix fun Effect.andThen(effect: Effect?): Set<Effect> = setOfNotNull(this, effect)
infix fun Set<Effect>.andThen(effect: Effect): Set<Effect> = this + effect
infix fun <S : State> ReducerResult<S>.andThen(other: Effect?) =
    ReducerResult.invoke(state, effects + other)

fun interface Reducer<S : State, E> {
    operator fun invoke(state: S, event: E): ReducerResult<S>
}

interface EffHandler<E : Effect> {
    suspend fun CoroutineScope.execute(eff: E, dispatch: (Event) -> Unit)

    companion object {
        suspend fun <E : Effect> EffHandler<E>.execute(
            coroutineScope: CoroutineScope,
            eff: E,
            dispatch: (Event) -> Unit,
        ) = coroutineScope.execute(eff, dispatch)
    }
}
