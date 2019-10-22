package fr.raksrinana.filesecure;

import fr.raksrinana.filesecure.config.FolderMapping;
import fr.raksrinana.filesecure.exceptions.MissingFolderException;
import fr.raksrinana.filesecure.files.FolderDifference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Process a pair of folder, one being the source of the backup, and the other the destination.
 * Singleton.
 * <p>
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 02/02/2018.
 *
 * @author Thomas Couchoud
 * @since 2018-02-02
 */
public class Processor{
	private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
	private final FolderMapping config;
	
	/**
	 * Constructor.
	 *
	 * @param config The configuration.
	 *
	 * @throws MissingFolderException If one of the folders doesn't exists.
	 */
	public Processor(final FolderMapping config) throws MissingFolderException{
		if(!config.getInput().toFile().exists()){
			throw new MissingFolderException(config.getInput());
		}
		if(!config.getOutput().toFile().exists()){
			throw new MissingFolderException(config.getOutput(), "output");
		}
		this.config = config;
	}
	
	/**
	 * Processes a pair of folders to backup.
	 * <p>
	 * If the input folder is "/A/B" and have this structure:
	 * A
	 * |-B
	 * | |-C
	 * | | |-1.txt
	 * | | |-2.txt
	 * | |-D
	 * | | |-3.txt
	 * | |-4.txt
	 * <p>
	 * The output folder "/Z" will then contain:
	 * Z
	 * |-C
	 * | |-1.txt
	 * | |-2.txt
	 * |-D
	 * | |-3.txt
	 * |-4.txt
	 */
	void process(){
		LOGGER.info("Processing ({}) {} ==> {}", config.getStrategy().name(), config.getInput(), config.getOutput());
		LOGGER.info("Building differences...");
		final var fd = new FolderDifference(config.getOutput(), config.getInput(), config.getRenameStrategy(), config.getOptions());
		fd.applyStrategy(config.getStrategy(), config.getFilters(), config.getExcludes());
	}
}