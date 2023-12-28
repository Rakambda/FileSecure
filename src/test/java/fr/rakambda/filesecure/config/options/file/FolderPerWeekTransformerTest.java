package fr.rakambda.filesecure.config.options.file;

import fr.rakambda.filesecure.exceptions.AbandonBackupException;
import fr.rakambda.filesecure.processor.FileMetadata;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FolderPerWeekTransformerTest{
	private final FolderPerWeekTransformer tested = new FolderPerWeekTransformer();
	
	@Mock
	private Path source;
	@Mock
	private Path originalOut;
	@Mock
	private FileMetadata metadata;
	
	@Test
	void itShouldWork() throws AbandonBackupException{
		when(metadata.getDate()).thenReturn(ZonedDateTime.of(2023, 5, 12, 10, 15, 12, 0, UTC));
		var base = Paths.get("/path");
		var out = Paths.get("/path/to/my/file.ext");
		
		assertThat(tested.apply(source, originalOut, base, out, metadata))
				.contains(Paths.get("/path/2023/05/2/to/my/file.ext"));
	}
}