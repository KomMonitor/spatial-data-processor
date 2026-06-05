# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Changed

- [maven-release-plugin] prepare for next development iteration ([aa246b7](https://github.com/KomMonitor/spatial-data-processor/commit/aa246b704e2af459c9020539956abbd00c1bc2c0))
- Pin Trivy action to save version ([a55923a](https://github.com/KomMonitor/spatial-data-processor/commit/a55923ac32dbbb95e44d228b25f8eb6150dc4342))

## [1.1.0]
> 10 Mar 2025

### Changed

- Merge branch 'master' into develop ([6de6de4](https://github.com/KomMonitor/spatial-data-processor/commit/6de6de4ff46babbe6a3b30a2d97d6a91b5a6f3b6))
- Raise cache actions version ([b6784d0](https://github.com/KomMonitor/spatial-data-processor/commit/b6784d0c2cc69e83b62e5bec6cc39218a613ddc5))
- Implement check for topological validity of geometries. if invalid try fix with buffer(0) ([558cf74](https://github.com/KomMonitor/spatial-data-processor/commit/558cf7408bcb663684be8a3c871cdc728e52e18d))
- [maven-release-plugin] Update CHANGELOG.md ([365b903](https://github.com/KomMonitor/spatial-data-processor/commit/365b9033cea4f6277bc6de57d7a0714f0261e83f))
- [maven-release-plugin] prepare release 1.1.0 ([b62aa0f](https://github.com/KomMonitor/spatial-data-processor/commit/b62aa0f7426afe4afeca87876462e401f47bdb5c))

## [1.0.4]
> 25 Sep 2024

### Changed

- [maven-release-plugin] prepare for next development iteration ([ca8762e](https://github.com/KomMonitor/spatial-data-processor/commit/ca8762e6f3dab04967b2bad7f5a3bb81b49ee36a))
- [maven-release-plugin] Update CHANGELOG.md ([540ee38](https://github.com/KomMonitor/spatial-data-processor/commit/540ee38cdd3eccecde82c90476a5a26adac459ad))
- [maven-release-plugin] prepare release 1.0.4 ([a3619a0](https://github.com/KomMonitor/spatial-data-processor/commit/a3619a08aa127ecd47c3ad2470115b6a2a2e763d))
- [maven-release-plugin] prepare for next development iteration ([92412ca](https://github.com/KomMonitor/spatial-data-processor/commit/92412ca48bcf1c40a149b1c5f0862e7387f63e6c))
- Pin Eclipse Temurin image version for Docker build ([d712bd9](https://github.com/KomMonitor/spatial-data-processor/commit/d712bd9c17478c1f24908d991b65b3e0ae7c21f5))

### Fixed

- Fix geometry collection type casting ([76f9ec6](https://github.com/KomMonitor/spatial-data-processor/commit/76f9ec60338b4fc8069398f15c8d8cb00f720779))

## [1.0.3]
> 14 Aug 2024

### Changed

- [maven-release-plugin] prepare for next development iteration ([24aaba5](https://github.com/KomMonitor/spatial-data-processor/commit/24aaba577224b243a16101a0b95a39bb8b23ff78))
- [maven-release-plugin] Update CHANGELOG.md ([377a3fe](https://github.com/KomMonitor/spatial-data-processor/commit/377a3fef44130363c295c53c1706dfc33cecf8c6))
- [maven-release-plugin] prepare release 1.0.3 ([8494d94](https://github.com/KomMonitor/spatial-data-processor/commit/8494d94fbccf433806fa473bdfe7196964447013))

### Fixed

- Fix retrieval of public spatial unit and public indicator timeseries without geometries by correcting Accept headers and URLs ([dfe59d4](https://github.com/KomMonitor/spatial-data-processor/commit/dfe59d4ba5fee997d35c2ee5fc7d45d1d2ed6c63))

## [1.0.2]
> 28 Jun 2024

### Changed

- [maven-release-plugin] prepare for next development iteration ([6953fcb](https://github.com/KomMonitor/spatial-data-processor/commit/6953fcbc657046bf23cb651f7b536f075b000d7e))
- Fetch kommonitor spatial unit and indicator features using simplification=medium to overcome topology issues during intersection ([791acc2](https://github.com/KomMonitor/spatial-data-processor/commit/791acc2cd95474cbbaf26a98f62189f5aacaab7e))
- Make simplifyGeometries query parameter configurable ([67991ba](https://github.com/KomMonitor/spatial-data-processor/commit/67991ba527b24599cb09357dd96c72f0404a1366))
- Set absolute and relative coverage to 0 if no coverage is available ([82f4677](https://github.com/KomMonitor/spatial-data-processor/commit/82f4677409966e201ab9d941574c258676bebf9b))
- Fetch spatial geometries using the delivered date components instead of all features to work with only those features applicable for the delivered date ([ecc8cf5](https://github.com/KomMonitor/spatial-data-processor/commit/ecc8cf58e006f2a6e21802095a3cfe6319762c00))
- [maven-release-plugin] Update CHANGELOG.md ([0c660c2](https://github.com/KomMonitor/spatial-data-processor/commit/0c660c2be913041eaf7e85bcc81cde72ca69c2a9))
- [maven-release-plugin] prepare release 1.0.2 ([44fbeea](https://github.com/KomMonitor/spatial-data-processor/commit/44fbeea3a83abfe6f927564f81cb426e98d8eeea))

### Fixed

- Fix error when an indicator does not provide indicator values for all spatial unit features ([cceff99](https://github.com/KomMonitor/spatial-data-processor/commit/cceff992ee725c39ceaf86e63f5555ec5143d55f))

## [1.0.1]
> 27 Jun 2024

### Changed

- [maven-release-plugin] prepare for next development iteration ([856f8d0](https://github.com/KomMonitor/spatial-data-processor/commit/856f8d02af118d98f6fdc75fb93d31a1bbf154d3))
- Modify CORS config ([7eae3e2](https://github.com/KomMonitor/spatial-data-processor/commit/7eae3e20f5390793065d60b14cb7547a8ce5a92d))
- Adjust release and CI configs ([eca5f68](https://github.com/KomMonitor/spatial-data-processor/commit/eca5f6890e7223eec7cb77550a414d9cdb5d2bb3))
- Merge branch 'develop' of https://github.com/KomMonitor/spatial-data-processor into develop ([9332255](https://github.com/KomMonitor/spatial-data-processor/commit/93322551dbe99e74a515894fad1bfaf2e9c646a9))
- [maven-release-plugin] Update CHANGELOG.md ([28c7760](https://github.com/KomMonitor/spatial-data-processor/commit/28c7760882b16232b111a1f56a18f6d63221c4e2))
- [maven-release-plugin] prepare release 1.0.1 ([393a4ed](https://github.com/KomMonitor/spatial-data-processor/commit/393a4edb426bd85a9028d07b6f77c87498ec16e6))

## [1.0.0]
>  6 Jun 2024

### Added

- Add initial controllers + generated classes ([bfed74a](https://github.com/KomMonitor/spatial-data-processor/commit/bfed74ab619deab764a5622aa520bd0f9053ff1b))
- Add first iteration of Job Queue System ([1e6d322](https://github.com/KomMonitor/spatial-data-processor/commit/1e6d322784806579ef9edd0dce3bd1660f9fb0a1))
- Add rudimentary logging ([0f42019](https://github.com/KomMonitor/spatial-data-processor/commit/0f42019141ce47cef86d11f0224a8b197869f895))
- Add generics to Process to avoid typecasting ([da19f30](https://github.com/KomMonitor/spatial-data-processor/commit/da19f30dcd2eeddf811f10b66781944c72912a45))
- Add basic data-management-api client ([a8ba459](https://github.com/KomMonitor/spatial-data-processor/commit/a8ba459389418009173025a351faa9771bcb723d))
- Add locationtech JTS Demo ([4a057cc](https://github.com/KomMonitor/spatial-data-processor/commit/4a057cc8e654288cdc65a15035def6ee545eabc2))
- Add home controller for swagger-ui redirecting ([26f753e](https://github.com/KomMonitor/spatial-data-processor/commit/26f753ee512671ba8c80c40336d8f2f761bfbb2e))
- Add utility class for spatial operations ([e9a828f](https://github.com/KomMonitor/spatial-data-processor/commit/e9a828f4f220b2e5fbceb3fe55584abfb0090c96))
- Add unit tests for spatial operation utils ([9bfc3bf](https://github.com/KomMonitor/spatial-data-processor/commit/9bfc3bffe738e98ff7de0ef00b8660ffcfe518b8))
- Add CRS checks ([b068ca9](https://github.com/KomMonitor/spatial-data-processor/commit/b068ca92fa0400eb1867f93b9730a944b7bbf254))
- Add basic Keycloak integration ([fdb2195](https://github.com/KomMonitor/spatial-data-processor/commit/fdb2195525a504a2f7785dae849f91f4848137f5))
- Add proportion of intersection calculation ([c575520](https://github.com/KomMonitor/spatial-data-processor/commit/c575520a0d38aa2122a8f116ca74d8eaa9a21707))
- Add retrofit logging ([024bdb9](https://github.com/KomMonitor/spatial-data-processor/commit/024bdb91e410667a8892a6881fdd4c732767de17))
- Add subselection of intersecting features ([ed0d328](https://github.com/KomMonitor/spatial-data-processor/commit/ed0d32805a77b44f0d4a98d0b8b1e245b15a586a))
- Add utilities for SimpleFeature handling ([080ddbb](https://github.com/KomMonitor/spatial-data-processor/commit/080ddbb06f360eedfd509df2e3d9a02a8f69622b))
- Add helper method for combining geometries ([2ccec13](https://github.com/KomMonitor/spatial-data-processor/commit/2ccec13983a4ad35aede50bd51a3f537d0771d7b))
- Add utility class for handling isochrones ([5340a66](https://github.com/KomMonitor/spatial-data-processor/commit/5340a66c87a1389af2d7c7ce3ddf016ef0ce3965))
- Add missing isochrone test dataset ([87d2c83](https://github.com/KomMonitor/spatial-data-processor/commit/87d2c836ed29275a824becbab5156f7d6925e79d))
- Add calculation of intersection proportion for single feature and feature collection ([1ad6674](https://github.com/KomMonitor/spatial-data-processor/commit/1ad667414830baada92c67006897d55ef152729e))
- Add residential area weighted intersection calculation ([23a1f8e](https://github.com/KomMonitor/spatial-data-processor/commit/23a1f8ec0b8b46668bfa1cac0f2f2142fac52e75))
- Add weighting parameter ([72e9083](https://github.com/KomMonitor/spatial-data-processor/commit/72e9083c895493675df2077e38a3e4a998f592ec))
- Add shape file loader ([98b4490](https://github.com/KomMonitor/spatial-data-processor/commit/98b449031e383fdbc345979b81ea42fb5d046368))
- Add additional intersection and filtering methods ([55ab640](https://github.com/KomMonitor/spatial-data-processor/commit/55ab640ef6a4fb122ac8b82dd61127ff6e59e7ab))
- Add unit tests for filtering operation ([4b08bc4](https://github.com/KomMonitor/spatial-data-processor/commit/4b08bc49ae90e0e0554484d4d799c2fa8135a22e))
- Add config properties ([a6bb94c](https://github.com/KomMonitor/spatial-data-processor/commit/a6bb94c667453f3b7231ac681e2f3369a06893ba))
- Add missing test files ([7613157](https://github.com/KomMonitor/spatial-data-processor/commit/7613157344845eeb2946b56a3767497f36d61ab9))
- Add unsecured filter chain non-keycloak settings ([d403867](https://github.com/KomMonitor/spatial-data-processor/commit/d4038673e150e4f3e6a7a6f246f0b5c74362d383))
- Add CORS ([9dd1fc1](https://github.com/KomMonitor/spatial-data-processor/commit/9dd1fc19776f0593194b9ce497b3245ec82f4b80))
- Add missing timeseries type ([de69981](https://github.com/KomMonitor/spatial-data-processor/commit/de69981838bbe0b014f779429c1a3689caf5067c))
- Add indicator timeseries values to spatial unit coverage ([4c7070c](https://github.com/KomMonitor/spatial-data-processor/commit/4c7070cc0d7987c21c808718389c695e1c0bf2f8))
- Add README ([0852997](https://github.com/KomMonitor/spatial-data-processor/commit/0852997363b9d27c7ee4a28ca928210cfb589c8e))
- Add GitHub actions ([cdd4448](https://github.com/KomMonitor/spatial-data-processor/commit/cdd444801cc123e6fc3d33b8524ea725303174d0))
- Add CORS config ([f35270c](https://github.com/KomMonitor/spatial-data-processor/commit/f35270ca0cbe2e3ddaf45674cc4517afb305fef5))
- Add git scm details ([89d81f6](https://github.com/KomMonitor/spatial-data-processor/commit/89d81f64716cdf5b09946110e71b607b4e865565))

### Changed

- Create .gitignore ([b3f8bf9](https://github.com/KomMonitor/spatial-data-processor/commit/b3f8bf94c590fdc020222eec2f4add0598c394ac))
- Init repository ([ae1efbe](https://github.com/KomMonitor/spatial-data-processor/commit/ae1efbef98e85ffdce35c8dcd07f80a6cee2f6e4))
- Regenerate auto-generated classes ([1561c93](https://github.com/KomMonitor/spatial-data-processor/commit/1561c9367628533d08ee17381ac5735e61dc7065))
- Regenerate code from specification ([4041be2](https://github.com/KomMonitor/spatial-data-processor/commit/4041be264c92b8e5b50a1ad74b2e9d63e44c1f13))
- Implement missing endpoints + restructure process discovery ([a531493](https://github.com/KomMonitor/spatial-data-processor/commit/a531493c82bd2d8cbf6421848aafcafb3812e3a6))
- Start data-management client ([5676a05](https://github.com/KomMonitor/spatial-data-processor/commit/5676a0597b304ce9cee4a6bdefda0fa9be1b070c))
- Regenerate models & add missing models ([c8b2dd0](https://github.com/KomMonitor/spatial-data-processor/commit/c8b2dd05b2818890793c48182ccb97663d8d7266))
- Update models ([65b118b](https://github.com/KomMonitor/spatial-data-processor/commit/65b118b5bef3c65cccc424a3cff4b4ce8145e28d))
- Refactor runnable to callable to allow for result output ([39e5ca4](https://github.com/KomMonitor/spatial-data-processor/commit/39e5ca4eba061385c4b885d2d7ec135210a92b5d))
- Enhance deps ([218eddf](https://github.com/KomMonitor/spatial-data-processor/commit/218eddf8fe4f937c44028fbe67a310c08d9e048c))
- Create Dockerfile ([553ea18](https://github.com/KomMonitor/spatial-data-processor/commit/553ea18230a712010d77fb43a6473c9933f77ee2))
- Disable csrf to allow POST requests ([1e40d4f](https://github.com/KomMonitor/spatial-data-processor/commit/1e40d4fc5aecee87900f0c4ddf4863b392679c8d))
- Refactor home-redirect to point to allowed location ([20de712](https://github.com/KomMonitor/spatial-data-processor/commit/20de7120234b805cab196ea5bb9f60cdfe013575))
- Refactor process registration to use Spring Bean Factories ([d5b9f64](https://github.com/KomMonitor/spatial-data-processor/commit/d5b9f64e05d598b66fffeb7d11d0a4910d4bfb2a))
- Regenerate models + api ([e9d0d28](https://github.com/KomMonitor/spatial-data-processor/commit/e9d0d28bff3b90bce250f56c441b1d4599a1f0ea))
- Refactor Job Storage to dedicated Bean ([973fe0e](https://github.com/KomMonitor/spatial-data-processor/commit/973fe0ede5f3338c448e588ee066c2993166821a))
- Change DataManagement URL request paths ([af127e8](https://github.com/KomMonitor/spatial-data-processor/commit/af127e86aa4b9d425b63e20e37779c758d8cbd25))
- Merge remote-tracking branch 'origin/develop' into develop ([0ad9dcd](https://github.com/KomMonitor/spatial-data-processor/commit/0ad9dcd8e847d024116a6fff7bf404b6ff659c37))
- Implement jobstore cleanup ([1a2857a](https://github.com/KomMonitor/spatial-data-processor/commit/1a2857af4c9e03291822b41a7bbb159e3940a461))
- Regenerate API and models and add endpoint for fetching results ([f89d8ed](https://github.com/KomMonitor/spatial-data-processor/commit/f89d8ed82df003bf6306b5bfdf648740e691082f))
- Implement result fetching from job store ([bdeb8ad](https://github.com/KomMonitor/spatial-data-processor/commit/bdeb8ad8969f36a8bf23f189482e24eca4d12db7))
- Generate models for IsochronePruneProcess results ([46c575f](https://github.com/KomMonitor/spatial-data-processor/commit/46c575f69b0859df697507aee86075bbb40f56f3))
- Enhance client requests for fetching geometries only and timeseries values only ([7cfeefc](https://github.com/KomMonitor/spatial-data-processor/commit/7cfeefc1b73c1dcce7a401e366dc219ac07e0600))
- Draft implementation for IsochronePruneProcess ([fe84ed6](https://github.com/KomMonitor/spatial-data-processor/commit/fe84ed6e7a0212e86294471ac23a9da34d62f15a))
- Restructure prune process algorithm ([fd96a38](https://github.com/KomMonitor/spatial-data-processor/commit/fd96a3833634994d17f7c9d8eccf030b0a7f14df))
- Implement overall indicator coverage calculation for each isochrone ([8d9dd47](https://github.com/KomMonitor/spatial-data-processor/commit/8d9dd473bc503e73b1370423e9c192dc3d6dfd81))
- Implement global coverage calculation ([237d07e](https://github.com/KomMonitor/spatial-data-processor/commit/237d07ead6dfac2b91edf04423ebd4dc28104605))
- Refactor isochrone prune algorithm ([2147860](https://github.com/KomMonitor/spatial-data-processor/commit/21478601863ae714de486ce03519a952177f7b0d))
- Enhance overall score calculation by grouping isochrone ranges ([d9921a1](https://github.com/KomMonitor/spatial-data-processor/commit/d9921a1ab415cad6d3bdf968923823f050a33943))
- Improve error handling ([e40973b](https://github.com/KomMonitor/spatial-data-processor/commit/e40973bd68428bae2057333b3985dcdba0f009fd))
- Change FeatureLoader interface return type ([7273fad](https://github.com/KomMonitor/spatial-data-processor/commit/7273fad346b3bb25ab4ef4d8302abab7bb5f4fea))
- Improve CRS check ([d6b4e74](https://github.com/KomMonitor/spatial-data-processor/commit/d6b4e7431711feaa0c4c5db75f0f36f0621cd265))
- Improve data loading concept ([0fed25a](https://github.com/KomMonitor/spatial-data-processor/commit/0fed25ae68dc17b5f1e26fa66d5d31c054dcd39c))
- Return exception message as result for failing jobs ([e3634a9](https://github.com/KomMonitor/spatial-data-processor/commit/e3634a9b5103f40184d279a6a7294ec0e47ae9a4))
- Finalize area weighted intersection ([f44b077](https://github.com/KomMonitor/spatial-data-processor/commit/f44b077aa89233aeb1bd78e58053259f2d33a22b))
- Implement residential area weighted intersection for overall scoring ([486c695](https://github.com/KomMonitor/spatial-data-processor/commit/486c695fcf7ec87b02211c45975cda7174c3fb66))
- Make data loading more flexible ([1d2645f](https://github.com/KomMonitor/spatial-data-processor/commit/1d2645f2315bda1be0861c0962f0520298094c14))
- Improve logging ([967061c](https://github.com/KomMonitor/spatial-data-processor/commit/967061c44f808bfcb9a2078d97bdddebbc246709))
- Use placeholder path ([925e708](https://github.com/KomMonitor/spatial-data-processor/commit/925e708b587b2ef9586914c4dcad70d1dc0b6d91))
- Update API specification documentation ([aaed6d9](https://github.com/KomMonitor/spatial-data-processor/commit/aaed6d9aad929947851ba864a7d5e83c9289f4aa))
- Implement authHeader forwarding for dataManagementClient ([aa0f18b](https://github.com/KomMonitor/spatial-data-processor/commit/aa0f18b7b98ef9921d9a158f1a9745872a5dbdb6))
- Merge branch 'main' into develop ([27dfc09](https://github.com/KomMonitor/spatial-data-processor/commit/27dfc093a5a81062d30013d67182c42e6ae584f3))
- Delete dummy data source ([4d90ead](https://github.com/KomMonitor/spatial-data-processor/commit/4d90ead0d4bde3af6fc3d5badf9ef911281e9699))
- Replace security scheme with bearer scheme ([d574b43](https://github.com/KomMonitor/spatial-data-processor/commit/d574b439696e34805dde95cb93578744000c38bc))
- Allow unauthenticated public requests for public data ([c62145b](https://github.com/KomMonitor/spatial-data-processor/commit/c62145b7cf7bff7f0c96e42617e10032af3ec528))
- Update isochrone payload test file ([4a488b6](https://github.com/KomMonitor/spatial-data-processor/commit/4a488b67938cf8717d9b3a84ade828c2b415bf43))
- Refine isochrone range parsing ([3f23c5d](https://github.com/KomMonitor/spatial-data-processor/commit/3f23c5dfc066628097d7092f2327959ca9410e10))
- Enhance isochrone parsing unit tests ([58deb4e](https://github.com/KomMonitor/spatial-data-processor/commit/58deb4e39b078cd5cb4613a78915044217f496be))
- Prefer CMD instead of ENTRYPOINT for Docker image ([7cb5d1d](https://github.com/KomMonitor/spatial-data-processor/commit/7cb5d1dad48ae661690c9b9ac7968ea83df747e1))
- Include toal indicator timeseries values in isochrone prune process result ([f5ce06d](https://github.com/KomMonitor/spatial-data-processor/commit/f5ce06dba67077c4a0ab3bce0014f754f96746e6))
- Create LICENSE ([1981c93](https://github.com/KomMonitor/spatial-data-processor/commit/1981c93cde2bc84285a182cbdc69dd4985a9991d))
- [maven-release-plugin] prepare release v1.0.0 ([d880955](https://github.com/KomMonitor/spatial-data-processor/commit/d880955d81eb687fb95699b821e71558706788ec))

### Fixed

- Fix swagger link + increase default log level during development ([26fb3ae](https://github.com/KomMonitor/spatial-data-processor/commit/26fb3ae4da1f1f20fe84f15dab87c76c3e9ae182))
- Fix empty ranges property check ([df74682](https://github.com/KomMonitor/spatial-data-processor/commit/df7468293d9d8a2ee3cc1f469ac0b0cb2eb3650d))
- Fix `userPermission`type ([3e0490a](https://github.com/KomMonitor/spatial-data-processor/commit/3e0490a63efe47a405b7f3395bc4b26c0a258bf6))
- Fix geometry type casting ([b801a46](https://github.com/KomMonitor/spatial-data-processor/commit/b801a464fc51a7b06b173ff5bd389a5556966c39))
- Fix range location in submitted ORS isochrone ([f193ef4](https://github.com/KomMonitor/spatial-data-processor/commit/f193ef45b4a8ef6fa7f46f44e832a8b32ce293ee))
- Fix residential area weighting method ([f8b35ff](https://github.com/KomMonitor/spatial-data-processor/commit/f8b35ffcd98fbe3e8914b19307ac41249af5b244))
- Fix CORS config ([c6cc124](https://github.com/KomMonitor/spatial-data-processor/commit/c6cc12458d66c8c9761b10f53f6bb18bb74f413a))
- Fix CORS again ([ab29fd5](https://github.com/KomMonitor/spatial-data-processor/commit/ab29fd51fc470429bb9f664520083a48907d2f89))

### Removed

- Remove deprecated keycloak adapter in favor of oauth2 resource server ([7aac7c8](https://github.com/KomMonitor/spatial-data-processor/commit/7aac7c87e33a8cbd935acb0f662c899773879414))

[unreleased]: https://github.com/KomMonitor/spatial-data-processor/compare/1.1.0..HEAD
[1.1.0]: https://github.com/KomMonitor/spatial-data-processor/compare/1.0.4..1.1.0
[1.0.4]: https://github.com/KomMonitor/spatial-data-processor/compare/1.0.3..1.0.4
[1.0.3]: https://github.com/KomMonitor/spatial-data-processor/compare/1.0.2..1.0.3
[1.0.2]: https://github.com/KomMonitor/spatial-data-processor/compare/1.0.1..1.0.2
[1.0.1]: https://github.com/KomMonitor/spatial-data-processor/compare/v1.0.0..1.0.1
[1.0.0]: https://github.com/KomMonitor/spatial-data-processor/compare/1.1.0..v1.0.0

<!-- generated by git-cliff -->
