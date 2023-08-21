package andrew.misterio05.app.card

import andrew.misterio05.app.Res
import andrew.misterio05.app.card.icons.Bolt
import andrew.misterio05.app.card.icons.CardIcons
import andrew.misterio05.app.card.icons.Shield
import andrew.misterio05.app.card.icons.ShieldExplosion
import andrew.misterio05.app.theme.Image
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

data class CardData(
    val type: Type,
    val title: String,
    val character: String,
    val boost: Int,
    val imageUrl: String,
    val quantity: Int,
) {
    data class Type(
        val icon: Image,
        val value: Int,
        val color: Color,
        val description: Description,
    ) {
        sealed interface Description {
            val basic: String

            data class Basic(override val basic: String = "") : Description

            data class Combat(
                override val basic: String = "",
                val immediateText: String = "",
                val duringText: String = "",
                val afterText: String = "",
            ) : Description
        }

        object Standard {
            fun atk(value: Int, description: Description.Combat) = Type(
                icon = Image.resource(Res.image.ic_explosion),
                value = value,
                color = Color(220, 48, 52),
                description = description,
            )

            fun def(value: Int, description: Description.Combat) = Type(
                icon = Image.vector(CardIcons.Shield),
                value = value,
                color = Color(44, 118, 172),
                description = description,
            )

            fun uni(value: Int, description: Description.Combat) = Type(
                icon = Image.vector(CardIcons.ShieldExplosion),
                value = value,
                color = Color(108, 78, 141),
                description = description,
            )

            fun sch(description: Description.Basic) = Type(
                icon = Image.vector(CardIcons.Bolt),
                value = -1,
                color = Color(252, 189, 113),
                description = description,
            )
        }
    }
}