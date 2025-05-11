
package collector.cards;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import collector.powers.DemisePower;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.InversionBeamEffect;

import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.applyToEnemy;
import static utilityClasses.Wiz.atb;

@NoPools @NoCompendium @Deprecated
public class InevitableDemise extends AbstractCollectorCard {
    public final static String ID = makeID(InevitableDemise.class.getSimpleName());
    // intellij stuff attack, enemy, common, 8, 4, , , ,
    //Removed due a combination of boring, bad performance and lack of general necessity, plus we needed a weak link to cut loose.

    public InevitableDemise() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 10;
       // baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new VFXAction(new InversionBeamEffect(MathUtils.random(m.hb.x + (m.hb.width / 3F), m.hb.x + ((m.hb.width / 3F) * 2F)))));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new DemisePower(m, 1));
    }

    public void upp() {
        upgradeDamage(3);
       // upgradeMagicNumber(3);
    }
}

