package expansioncontent.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.DiscardPileToTopOfDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;


public class SuperWhirlingFlame extends AbstractExpansionCard {
    public final static String ID = makeID(SuperWhirlingFlame.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, ,

    public SuperWhirlingFlame() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = 9;//Idk why these values were put in wrong, fixed them.
        isMultiDamage = true;
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_collector.png", "expansioncontentResources/images/1024/bg_boss_collector.png");

        tags.add(expansionContentMod.STUDY_COLLECTOR);
        tags.add(expansionContentMod.STUDY);
        expansionContentMod.loadJokeCardImage(this, "SuperWhirlingFlame.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        allDmg(AbstractGameAction.AttackEffect.FIRE);
        atb(new DiscardPileToTopOfDeckAction(p));
    }

    public void upp() {
        upgradeDamage(4);
    }
}