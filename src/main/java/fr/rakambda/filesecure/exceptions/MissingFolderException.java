package fr.rakambda.filesecure.exceptions;

import lombok.Getter;
import java.io.Serial;
import java.nio.file.Path;

/**
 * Exception raised when a folder doesn't exists.
 */
public class MissingFolderException extends IllegalStateException{
	@Serial
	private static final long serialVersionUID = -2933870594840515527L;
	@Getter
	private final Path path;
	
	/**
	 * Constructor.
	 *
	 * @param path The path of the folder that doesn't exists.
	 */
	public MissingFolderException(Path path){
		this(path, "input");
	}
	
	/**
	 * Constructor.
	 *
	 * @param path The path of the folder that doesn't exists.
	 * @param kind The kind of the folder (input, output, ...).
	 */
	public MissingFolderException(Path path, String kind){
		super("The " + kind + " folder " + path + " doesn't exists");
		this.path = path;
	}
}
