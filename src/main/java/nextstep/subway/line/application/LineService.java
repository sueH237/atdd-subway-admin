package nextstep.subway.line.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.LineRepository;
import nextstep.subway.line.dto.LineRequest;
import nextstep.subway.line.dto.LineResponse;

@Service
@Transactional
public class LineService {
	private LineRepository lineRepository;

	public LineService(LineRepository lineRepository) {
		this.lineRepository = lineRepository;
	}

	public LineResponse saveLine(LineRequest request) {
		Line persistLine = lineRepository.save(request.toLine());
		return LineResponse.of(persistLine);
	}

	public List<LineResponse> findAllLines() {
		final List<Line> lines = lineRepository.findAll();
		return lines.stream().map(it -> LineResponse.of(it)).collect(Collectors.toList());
	}

	public LineResponse findLine(Long id) {
		return LineResponse.of(lineRepository.findById(id).get());
	}

	public void deleteLineById(Long id) {
		lineRepository.deleteById(id);
	}
}
