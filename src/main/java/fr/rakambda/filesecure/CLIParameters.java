package fr.rakambda.filesecure;

import lombok.Getter;
import lombok.NoArgsConstructor;
import picocli.CommandLine;

@NoArgsConstructor
@Getter
@CommandLine.Command(name = "filesecure", mixinStandardHelpOptions = true)
public class CLIParameters{
	@CommandLine.Option(names = {
			"-c",
			"--config"
	},
			description = "The path to the configuration file",
			required = true)
	public String configurationFile;
	@CommandLine.Option(names = {
			"--dry"
	},
			description = "Performs a dry run")
	public boolean dryRun;
}

