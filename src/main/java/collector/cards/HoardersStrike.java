package collector.cards;

import collector.CollectorCollection;
import collector.actions.NewDrawCollectiblesActionSet;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import static collector.CollectorMod.makeID;
import static utilityClasses.Wiz.atb;

public class HoardersStrike extends AbstractCollectorCard {
    public final static String ID = makeID(HoardersStrike.class.getSimpleName());

    public HoardersStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 0;
        tags.add(CardTags.STRIKE);
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        atb(new NewDrawCollectiblesActionSet(false, this.magicNumber, true, AbstractDungeon.cardRandomRng));
    }

    public void applyPowers() {
        this.baseDamage = (AbstractDungeon.player.masterDeck.size() + CollectorCollection.collection.size());
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + damage + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0] + damage + cardStrings.EXTENDED_DESCRIPTION[1];
        initializeDescription();
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}