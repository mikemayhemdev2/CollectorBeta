package collector.cards;
import collector.powers.DoomPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.*;

public class ThornWhip extends AbstractCollectorCard {
    public final static String ID = makeID(ThornWhip.class.getSimpleName());
    // intellij stuff attack, enemy, rare, 11, 1, , , , 

    public ThornWhip() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 6;
        //       baseMagicNumber = magicNumber = 3;
        isMultiDamage = true;
        //       cardsToPreview = new Shiv();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : DFL.activeMonsterList()) {
            if (!mon.isDeadOrEscaped()) {
                addToBot(new DamageAction(mon, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                int unblockedDamage = Math.max(damage - mon.currentBlock, 0); // Changed `m` to `monster`
                if (unblockedDamage > 0) {
                    applyToEnemy(mon, new DoomPower(mon, unblockedDamage));
                }
            }
        }
        /*
        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        forAllMonstersLiving(q -> applyToEnemy(q, new Bruise(q, magicNumber)));
        makeInHand(new Shiv());
         */
    }

    public void upp() {
        upgradeDamage(2);
//        upgradeMagicNumber(1);
    }
}