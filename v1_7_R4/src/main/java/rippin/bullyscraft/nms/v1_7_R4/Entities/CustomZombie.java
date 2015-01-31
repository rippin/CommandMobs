package rippin.bullyscraft.nms.v1_7_R4.Entities;


import net.minecraft.server.v1_7_R4.EntityZombie;
import net.minecraft.server.v1_7_R4.PathfinderGoalSelector;
import net.minecraft.server.v1_7_R4.World;
import org.bukkit.craftbukkit.v1_7_R4.util.UnsafeList;

import java.lang.reflect.Field;
import java.util.List;

public class CustomZombie extends EntityZombie implements CustomEntity {

    public CustomZombie(World world)
    {
        super(world);
        try{
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);
            List bGoal =  (List) bField.get(goalSelector); bGoal.clear();
            List bTarget =  (List) bField.get(targetSelector); bTarget.clear();
            List cGoal =  (List) cField.get(goalSelector); cGoal.clear();
            List cTarget =  (List) cField.get(targetSelector); cTarget.clear();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void makeSound(String s, float f, float f1) {}
    @Override
    public void move(double d0, double d1, double d2){}
}
