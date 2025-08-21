package collector.cards;

import collector.CollectorCollection;
import collector.cards.collectibles.AbstractCollectibleCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PurgeField;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sneckomod.SneckoMod;
import utilityClasses.DFL;
import utilityClasses.Later.LaterAction;
import java.util.UUID;
import static collector.CollectorMod.makeID;

public class DarkApotheosis extends AbstractCollectorCard {
    public final static String ID = makeID(DarkApotheosis.class.getSimpleName());
    // intellij stuff skill, self, rare, , , , , , 

    public DarkApotheosis() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
        this.tags.add(SneckoMod.BANNEDFORSNECKO);
        this.tags.add(CardTags.HEALING);
        PurgeField.purge.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        DFL.atb(new LaterAction(() -> {
            for (AbstractCard c : CollectorCollection.collection.group) {
                //DFL.pl().exhaustPile.removeCard(c);
                if (this.upgraded) {
                    c.upgrade();
                    DFL.atb(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, false));
                } else {
                    DFL.pl().discardPile.addToBottom(c.makeStatEquivalentCopy());
                }
            }
        }));

    }

    public boolean compareUUIDtoPile(UUID cardUUID){
        for (AbstractCard c : CollectorCollection.collection.group){
            if (c.uuid == cardUUID){
                return true;
            }
        }
        return false;
    }

    public void upp() {
        uDesc();
    }
}