package collector.cards;

import collector.actions.kaboomAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ScreenOnFireEffect;
import utilityClasses.DFL;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class FiendFire extends AbstractCollectorCard {
    public final static String ID = makeID(FiendFire.class.getSimpleName());

    public FiendFire() {
        super(ID, 5, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
//        this.baseMagicNumber = 2;        this.baseSecondMagic = 2;       this.baseDamage = baseMagicNumber;
        this.baseDamage = 5;
        exhaust = true;
        this.isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new kaboomAction());
        int times = 0;

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int times = DFL.pl().exhaustPile.size();
            }
        });

        atb(new AbstractGameAction() {//Staggered atb's to ensure the exhaust actions and counting all occurs BEFORE the game decides to try and count.
            @Override
            public void update() {
                isDone = true;
                if (!DFL.pl().exhaustPile.isEmpty()){
                    for (int t = 0; t < times; t++) {//Note due to the order of statments, this is not 0.
                        addToBot(new VFXAction(new ScreenOnFireEffect()));
                        allDmg(AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                    }
                }
            }
        });
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse){
            return false;
        }else if (DFL.pl().hand.size() < 2) {
            this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
            return false;
        }
        return true;
    }

/*    public void applyPowers() {
        int add = EnergyPanel.totalCount;
        if (AbstractDungeon.player.hasRelic(ChemicalX.ID)) {add += 2;}
        this.baseDamage = (secondMagic += (add * magicNumber));
        super.applyPowers();
    }*/


    public void upp() {
  //      upgradeSecondMagic(1);
 //       upgradeMagicNumber(1);
//        upgradeDamage(1);
        this.selfRetain = true;
        uDesc();
    }
}