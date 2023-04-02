package Weapon.Sword;

import Hero.Hero;
import Monster.Monster;
import Weapon.Weapon;

public class Sword extends Weapon {

    public Sword() {
        super("Sword", 4, 10, "Slash");
        this.maxSkillCooldown = 4;
    }

    @Override
    public boolean useWeaponSkill(Hero player, Monster enemy) {
        if (isWeaponSkillAvailable()) {
            int hitRating = player.calculateHitRating(enemy) - this.getAccuracy();
            boolean isAttackSuccessful = player.isAttackSuccessful(hitRating);
            this.setCurrentSkillCooldown(this.getMaxSkillCooldown());
            if (isAttackSuccessful) {
                this.inflictDamageOnSkill(player, enemy);
            } else {
                player.setLife(player.getLife() - 5);
                System.out.println("You tripped on your sword, hurting yourself for " + 5 + " damages.");
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void inflictDamageOnSkill(Hero player, Monster enemy) {
        int damage = this.getSkillDamage(player);
        enemy.setLife(enemy.getLife() - damage);
        System.out.println("You perform a powerful slash towards the " + enemy.getName() + " !");
        System.out.println("It dealt the honest amount of " + damage + " points of damage.");
    }

    @Override
    public int getSkillDamage(Hero player) {
        return player.getStrength() + this.getDamage() + (player.getLevel() - 1) * 2;
    }

    @Override
    public int getAttackDamage(Hero player) {
        return player.getStrength() + this.getDamage();
    }
}