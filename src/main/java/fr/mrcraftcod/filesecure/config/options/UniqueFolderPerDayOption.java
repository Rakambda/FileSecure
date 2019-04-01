package fr.mrcraftcod.filesecure.config.options;

import fr.mrcraftcod.filesecure.config.Option;
import fr.mrcraftcod.filesecure.files.DesiredTarget;
import fr.mrcraftcod.nameascreated.NewFile;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Path;

/**
 * Move a file into a folder yyyy-mm-dd.
 * <p>
 * Created by mrcraftcod (MrCraftCod - zerderr@gmail.com) on 2019-03-26.
 *
 * @author Thomas Couchoud
 * @since 2019-03-26
 */
public class UniqueFolderPerDayOption implements Option{
	private static final Logger LOGGER = LoggerFactory.getLogger(UniqueFolderPerDayOption.class);
	
	public UniqueFolderPerDayOption(final JSONObject json){
	
	}
	
	@Override
	public void apply(final Path originFile, final DesiredTarget desiredTarget, final NewFile fileName, final Path folder){
		try{
			final var date = fileName.getDate();
			desiredTarget.setTargetFolder(folder.resolve(String.format("%4d-%02d-%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth())));
		}
		catch(final Exception e){
			LOGGER.error("Failed to build unique day folder for {} in {}", fileName, folder, e);
		}
	}
	
	@Override
	public int getPriority(){
		return 10;
	}
}
