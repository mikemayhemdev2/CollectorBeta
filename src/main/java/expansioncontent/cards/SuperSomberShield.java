package expansioncontent.cards;
import collector.cards.OnPyreCard;
import collector.powers.AddCopyNextTurnPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import expansioncontent.expansionContentMod;


public class SuperSomberShield extends AbstractExpansionCard implements OnPyreCard {
    public final static String ID = makeID(SuperSomberShield.class.getSimpleName());
    // intellij stuff skill, self, common, , , 7, 3, ,

    public SuperSomberShield() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 9;
        this.setBackgroundTexture("expansioncontentResources/images/512/bg_boss_collector.png", "expansioncontentResources/images/1024/bg_boss_collector.png");

        tags.add(expansionContentMod.STUDY_COLLECTOR);
        tags.add(expansionContentMod.STUDY);

        isPyre();
        expansionContentMod.loadJokeCardImage(this, "SuperSomberShield.png");
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void onPyred(AbstractCard card) {
        applyToSelfTop(new AddCopyNextTurnPower(card));
    }

    public void upp() {
        upgradeBlock(3);
    }
}