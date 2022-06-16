package de.opticks.rougetale.common.entity;

import com.mojang.math.Vector3d;
import net.minecraft.network.protocol.status.ServerStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.world.NoteBlockEvent;
import org.apache.logging.log4j.LogManager;

import java.util.Objects;

public class GhostEntity extends Animal implements FlyingAnimal{

    @Override
    public boolean isFlying() {
        return true;
    }

    public GhostEntity(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level,AgeableMob mob){
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.goalSelector.addGoal(1, new GhostAttackGoal(this, 1.0, false));
        //this.goalSelector.addGoal(8, new Bee.BeeWanderGoal());
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 5.0).add(Attributes.MOVEMENT_SPEED, 0.25).add(Attributes.ATTACK_DAMAGE, 2).add(Attributes.FOLLOW_RANGE,10).add(Attributes.FLYING_SPEED, 1);
    }

    public class GhostAttackGoal extends MeleeAttackGoal {
        private final GhostEntity ghost;
        private int raiseArmTicks;

        public GhostAttackGoal(GhostEntity p_26019_, double p_26020_, boolean p_26021_) {
            super(p_26019_, p_26020_, p_26021_);
            this.ghost = p_26019_;
        }

        public void start() {
            super.start();
            this.raiseArmTicks = 0;
        }

        public void stop() {
            super.stop();
            this.ghost.setAggressive(false);
        }

        public void tick() {
            super.tick();
            ++this.raiseArmTicks;
            if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
                this.ghost.setAggressive(true);
            } else {
                this.ghost.setAggressive(false);
            }

        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getTarget() != null){

            Vec3 playerPosition = (this.getTarget()).position();
            Vec3 distVec = new Vec3(playerPosition.x-this.position().x,playerPosition.y+ 1.3 -this.position().y,playerPosition.z-this.position().z);
            double distLength = Math.sqrt(Math.pow(distVec.x,2) + Math.pow(distVec.y,2) + Math.pow(distVec.z,2));
            Vec3 distVecNorm = new Vec3(distVec.x/distLength, distVec.y/distLength,distVec.z/distLength);
            this.move(MoverType.SELF, new Vec3(distVecNorm.x/20,distVecNorm.y/20,distVecNorm.z/20));
            }
        }
    }
