//package ch.skyfy.homes.callbacks
//
//import net.fabricmc.fabric.api.event.Event
//import net.fabricmc.fabric.api.event.EventFactory
//import net.minecraft.entity.Entity
//import net.minecraft.entity.MovementType
//import net.minecraft.util.ActionResult
//import net.minecraft.util.math.Vec3d
//
//fun interface EntityMoveCallback {
//    companion object {
//        @JvmField
//        val EVENT: Event<EntityMoveCallback> = EventFactory.createArrayBacked(EntityMoveCallback::class.java) { listeners ->
//            EntityMoveCallback { entity, movementType, movement ->
//                for (listener in listeners) {
//                    val result = listener.onMove(entity, movementType, movement)
//                    if(listener.onMove(entity, movementType, movement) != ActionResult.PASS)return@EntityMoveCallback result
//                }
//                ActionResult.PASS
//            }
//        }
//    }
//
//    fun onMove(entity: Entity, movementType: MovementType, movement: Vec3d) : ActionResult
//}