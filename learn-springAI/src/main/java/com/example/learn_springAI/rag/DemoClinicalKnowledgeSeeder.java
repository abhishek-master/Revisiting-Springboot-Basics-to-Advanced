package com.example.learn_springAI.rag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Loads small, generic decision-support snippets for demos. Replace with your hospital-owned protocols in production.
 */
@Component
@ConditionalOnProperty(prefix = "app.knowledge", name = "seed-demo", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class DemoClinicalKnowledgeSeeder implements ApplicationRunner {

    private final RagService ragService;

    @Override
    public void run(ApplicationArguments args) {
        List<DemoChunk> chunks = demoChunks();
        for (DemoChunk chunk : chunks) {
            ragService.ingestChunk(chunk.text(), chunk.metadata());
        }
        log.info("Demo clinical knowledge seeded ({} chunks). Disable app.knowledge.seed-demo for production.", chunks.size());
    }

    private static List<DemoChunk> demoChunks() {
        return List.of(
                new DemoChunk(
                        """
                                Fast OPD — focal infection / cellulitis screen: assess spreading erythema, warmth, \
                                tenderness, lymphangitic streaking, systemic signs (fever, tachycardia). \
                                Marking borders on skin can help document spread on reassessment. Consider risk factors \
                                (diabetes, immunosuppression) when planning escalation or imaging.""",
                        Map.of("source", "Demo-OPD-Skin-Infection", "topic", "dermatology")),
                new DemoChunk(
                        """
                                Diabetes — foot complaint: inspect interdigital spaces, ulcers, callus, deformity; \
                                assess pedal pulses and sensation when neuropathy is a concern. Document monofilament \
                                or vibration testing per local protocol if available.""",
                        Map.of("source", "Demo-Diabetes-Foot", "topic", "endocrine")),
                new DemoChunk(
                        """
                                Hypertension — elevated BP in clinic: confirm with repeat measurement; consider \
                                medication adherence, pain, caffeine, white-coat effect, and secondary causes when \
                                resistant. Align targets and follow-up with your institution’s guideline.""",
                        Map.of("source", "Demo-HTN-OPD", "topic", "cardiovascular")),
                new DemoChunk(
                        """
                                Respiratory — wheeze / cough: peak flow or spirometry when indicated; screen for \
                                exacerbation triggers, oxygen saturation, and work of breathing. Document inhaler \
                                technique and prior steroid or ER use.""",
                        Map.of("source", "Demo-Resp-OPD", "topic", "respiratory"))
        );
    }

    private record DemoChunk(String text, Map<String, Object> metadata) {
    }

}
